var gulp = require('gulp');
var config = {
    "node": "./node_modules/",
    "libs": "./src/main/webapp/libs/",
    "src": "./src/main/typescript/",
    "app": "./src/main/webapp/app/"
};

gulp.task('default', function () {
    console.log("default");
});

gulp.task('copy', ['copy:html', 'copy:css']);

gulp.task('copy:html', function () {
    gulp.src(config.src + '**/*.html').pipe(gulp.dest(config.app));
});

gulp.task('copy:css', function () {
    gulp.src(config.src + '**/*.css').pipe(gulp.dest(config.app));
});

gulp.task('copy:libs', function () {
    gulp.src(config.node + '@angular/**').pipe(gulp.dest(config.libs + '@angular'));
    gulp.src(config.node + 'angular-in-memory-web-api/**').pipe(gulp.dest(config.libs + 'angular-in-memory-web-api'));
    gulp.src(config.node + 'rxjs/**').pipe(gulp.dest(config.libs + 'rxjs'));
    gulp.src(config.node + 'bootstrap/dist/**').pipe(gulp.dest(config.libs + 'bootstrap'));
    gulp.src(config.node + 'core-js/**').pipe(gulp.dest(config.libs + 'core-js'));
    gulp.src(config.node + 'reflect-metadata/**').pipe(gulp.dest(config.libs + 'reflect-metadata'));
    gulp.src(config.node + 'zone.js/dist/**').pipe(gulp.dest(config.libs + 'zone.js'));
    gulp.src(config.node + 'systemjs/dist/**').pipe(gulp.dest(config.libs + 'systemjs'));
});


gulp.task('watch:copy', function () {
    gulp.watch([config.src + '**/*.html',config.src + '**/*.css'], ['copy']);
});
