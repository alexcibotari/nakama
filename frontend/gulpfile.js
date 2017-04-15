var gulp = require('gulp');
var ts = require("gulp-typescript");
var tsProject = ts.createProject("tsconfig.json");

var config = {
    "node": "./node_modules/",
    "resourceLibs": "./src/main/resources/public/libs/",
    "typescript": "./src/main/typescript/",
    "resourceApp": "./src/main/resources/public/app/",
    "buildApp": "./build/resources/main/public/app/"
};

gulp.task('default', ['copy:resource', 'compile:resource']);

gulp.task('copy:resource', ['copy:resource:libs', 'copy:resource:src']);

gulp.task('copy:resource:src', ['copy:resource:html', 'copy:resource:css']);

gulp.task('copy:resource:html', function () {
    gulp.src(config.typescript + '**/*.html').pipe(gulp.dest(config.resourceApp));
});

gulp.task('copy:resource:css', function () {
    gulp.src(config.typescript + '**/*.css').pipe(gulp.dest(config.resourceApp));
});

gulp.task('copy:build:html', function () {
    gulp.src(config.typescript + '**/*.html').pipe(gulp.dest(config.buildApp));
});

gulp.task('copy:build:css', function () {
    gulp.src(config.typescript + '**/*.css').pipe(gulp.dest(config.buildApp));
});


gulp.task('copy:resource:libs', function () {
    gulp.src(config.node + '@angular/**').pipe(gulp.dest(config.resourceLibs + '@angular'));
    gulp.src(config.node + '@covalent/**').pipe(gulp.dest(config.resourceLibs + '@covalent'));
    gulp.src(config.node + 'angular-in-memory-web-api/**').pipe(gulp.dest(config.resourceLibs + 'angular-in-memory-web-api'));
    gulp.src(config.node + 'rxjs/**').pipe(gulp.dest(config.resourceLibs + 'rxjs'));
    gulp.src(config.node + 'bootstrap/dist/**').pipe(gulp.dest(config.resourceLibs + 'bootstrap'));
    gulp.src(config.node + 'core-js/**').pipe(gulp.dest(config.resourceLibs + 'core-js'));
    gulp.src(config.node + 'zone.js/dist/**').pipe(gulp.dest(config.resourceLibs + 'zone.js'));
    gulp.src(config.node + 'systemjs/dist/**').pipe(gulp.dest(config.resourceLibs + 'systemjs'));
    gulp.src(config.node + 'hammerjs/**').pipe(gulp.dest(config.resourceLibs + 'hammerjs'));
});

gulp.task('compile:resource', ['compile:resource:tsc']);

gulp.task('compile:resource:tsc', function () {
    return tsProject.src()
        .pipe(tsProject())
        .js.pipe(gulp.dest(tsProject.options.outDir));
});

gulp.task('compile:build:tsc', ['compile:resource:tsc'], function () {
    return tsProject.src()
        .pipe(tsProject())
        .js.pipe(gulp.dest(config.buildApp));
});


gulp.task('watch', function () {
    gulp.watch([config.typescript + '**/*.html'], ['copy:resource:html']);
    gulp.watch([config.typescript + '**/*.css'], ['copy:resource:css']);
    gulp.watch([config.typescript + '**/*.ts'], ['compile:resource:tsc']);
});

gulp.task('watch-dev', function () {
    gulp.watch([config.typescript + '**/*.html'], ['copy:resource:html', 'copy:build:html']);
    gulp.watch([config.typescript + '**/*.css'], ['copy:resource:css', 'copy:build:css']);
    gulp.watch([config.typescript + '**/*.ts'], ['compile:resource:tsc', 'compile:build:tsc']);
});
