/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
    System.config({
        paths: {
            // paths serve as alias
            'libs:': 'libs/'
        },
        // map tells the System loader where to look for things
        map: {
            // our app is within the app folder
            app: 'app',
            // angular bundles
            '@angular/animations': 'libs:@angular/animations/bundles/animations.umd.js',
            '@angular/animations/browser': 'libs:@angular/animations/bundles/animations-browser.umd.js',
            '@angular/core': 'libs:@angular/core/bundles/core.umd.js',
            '@angular/common': 'libs:@angular/common/bundles/common.umd.js',
            '@angular/compiler': 'libs:@angular/compiler/bundles/compiler.umd.js',
            '@angular/platform-browser': 'libs:@angular/platform-browser/bundles/platform-browser.umd.js',
            '@angular/platform-browser/animations': 'libs:@angular/platform-browser/bundles/platform-browser-animations.umd.js',
            '@angular/platform-browser-dynamic': 'libs:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'libs:@angular/http/bundles/http.umd.js',
            '@angular/router': 'libs:@angular/router/bundles/router.umd.js',
            '@angular/forms': 'libs:@angular/forms/bundles/forms.umd.js',
            //UI
            '@angular/material': 'libs:@angular/material/bundles/material.umd.js',
            '@angular/flex-layout' : 'libs:@angular/flex-layout/bundles/flex-layout.umd.js',
            'hammerjs': 'libs:hammerjs',
            '@covalent/core': 'libs:@covalent/core/core.umd.js',
            // other libraries
            'rxjs': 'libs:rxjs',
            'angular-in-memory-web-api': 'libs:angular-in-memory-web-api',
        },
        //packages tells the System loader how to load when no filename and/or no extension
        packages: {
            app: {
                main: 'main.js',
                defaultExtension: 'js'
            },
            rxjs: {
                defaultExtension: 'js'
            }
        }
    });
})(this);
