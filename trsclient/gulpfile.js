/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var gulp = require('gulp');
var war = require('gulp-war');
var zip = require('gulp-zip');

gulp.task('war', function () {
    gulp.src(["dist/*"])
        .pipe(war({
            welcome: 'index.html',
            displayName: 'TweetRecommenderSystem'
        }))
        .pipe(zip('trsclient.war'))
        .pipe(gulp.dest("./target"));
});