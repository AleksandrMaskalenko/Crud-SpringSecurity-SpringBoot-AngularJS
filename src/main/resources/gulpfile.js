var gulp = require('gulp');
var concat = require('gulp-concat');
var inject = require('gulp-inject');
var mainBowerFiles = require('main-bower-files');


gulp.task('css', function(){
    gulp.src([
        "./public/app/bower-files/bootstrap.css",
        "./public/app/bower-files/select2.css",
        "./public/app/css/style.css"
    ])
        .pipe(concat('style.css'))
        .pipe(gulp.dest('./static/app'));
});

gulp.task('js', function() {
    gulp.src([
        "./public/app/bower-files/jquery.js",
        "./public/app/bower-files/angular.js",
        "./public/app/bower-files/angular-ui-router.js",
        "./public/app/bower-files/select2.js",
        "./public/app/bower-files/index.js",
        "./public/app/js/app.js",
        "./public/app/js/route.js",
        "./public/app/js/services/auth-service.js",
        "./public/app/js/controllers/nav.js",
        "./public/app/js/controllers/login.js",
        "./public/app/js/controllers/register.js",
        "./public/app/js/controllers/users.js",
        "./public/app/js/controllers/page-not-found.js",
        "./public/app/js/controllers/song-controller.js",
        "./public/app/js/controllers/playlist.js",
        "./public/app/js/controllers/add-song.js",
        "./public/app/js/controllers/edit-song.js"
    ])
        .pipe(concat('scripts.js'))
        .pipe(gulp.dest('./static/app'));
});

gulp.task('html', function () {
    gulp.src('./public/app/views/**/*.html')
        .pipe(gulp.dest('./static/app/views'));
});

gulp.task('build', function() {
    gulp.start(['css', 'js', 'html']);
});

gulp.task('index', function () {

    var tpl_src = [
        "./static/app/**/*.js",
        "./static/app/**/*.css"];

    return gulp.src('./public/app/index.html')
        .pipe(inject(gulp.src(tpl_src)))
        .pipe(gulp.dest('./static'));

});

gulp.task('bower', function() {
    return gulp.src(mainBowerFiles())
        .pipe(gulp.dest('./public/app/bower-files'));
});