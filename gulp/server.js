'use strict';

var path = require('path');
var gulp = require('gulp');
var browserSync = require('browser-sync');
var path = require('path');
var runSequence = require('run-sequence');
var merge = require('merge-stream');
var webpack = require('webpack-stream');
var webpackConfig = require('./../webpack.config.js');
var autoprefixer = require('autoprefixer-stylus');
var fileinclude = require('gulp-file-include');
var plumber = require('gulp-plumber');
var normalize = require('normalize');
var httpProxy = require('http-proxy');

var paths = {
    src: path.resolve(__dirname, '../js'),
    dest: path.resolve(__dirname, '../build')
};

var $ = require('gulp-load-plugins')({
    pattern: ['gulp-*', 'del', 'main-bower-files']
});

var sGridPath = path.dirname(require.resolve('s-grid'));

var proxy = httpProxy.createProxyServer({
   target: 'http://localhost:8080'
});

// Proxy any api calls to the back end server
var proxyMiddleware = function(req, res, next) {
    var path = req.url.split('/');
    if (/api/.test(path[1])) {
        proxy.web(req, res);
    } else {
        next();
    }
};

// Clean temp
gulp.task('clean:tmp', function(done) {
    $.del(['./.tmp'], done);
});

// Run server
gulp.task('browser-sync', function() {
    browserSync({
        server: {
            baseDir: ['./', './.tmp']
        },
        port: 3000
    });
});

// Reload browser sync
gulp.task('bs-reload', function () {
    browserSync.reload();
});

// Bundle js
gulp.task('js', function() {
    return gulp.src(paths.src + '/main.js')
        .pipe(webpack(webpackConfig))
        .pipe(gulp.dest(paths.dest + '/'));
});

// JS Watch
gulp.task('js-watch', ['js'], function() {
    browserSync.reload();
});

// Local styles
gulp.task('styles:local', function() {
    return gulp.src(['./styles/main.styl'])
        .pipe(plumber())
        .pipe($.sourcemaps.init())
        .pipe($.stylus({
            include: [normalize.path, sGridPath],
            use: [autoprefixer('last 2 versions')]
        }))
        .pipe($.sourcemaps.write(''))
        .pipe(gulp.dest('.tmp/styles'))
        .pipe(browserSync.reload({stream:true}));
});

// Watch
gulp.task('watch', function() {
    // Watch index.html
    gulp.watch('index.html', ['bs-reload']);

    // Watch JS
    gulp.watch('./js/**/*.js', ['js-watch']);

    // Watch Stylus
    gulp.watch('./styles/**/*.styl', ['styles:local']);
});

// Serve
gulp.task('serve', function() {
    runSequence('clean:tmp', ['browser-sync', 'styles:local', 'js', 'watch']);
});
