/*
 * Symphony - A modern community (forum/BBS/SNS/blog) platform written in Java.
 * Copyright (C) 2012-present, b3log.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
/**
 * @file frontend tool.
 *
 * @author <a href="http://vanessa.b3log.org">Liyuan Li</a>
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.8.0.2, Mar 17, 2019
 */

'use strict'

var gulp = require('gulp')
var concat = require('gulp-concat')
var cleanCSS = require('gulp-clean-css')
var uglify = require('gulp-uglify')
var sass = require('gulp-sass')
var rename = require('gulp-rename')
var del = require('del')

const STATIC_ROOT = './src/main/resources/WEB-INF/static'

function sassProcess () {
  return gulp.src(`${STATIC_ROOT}/scss/*.scss`).
    pipe(sass({outputStyle: 'compressed', includePaths: ['node_modules']}).
      on('error', sass.logError)).
    pipe(gulp.dest(`${STATIC_ROOT}/css`))
}

function sassProcessWatch () {
  gulp.watch(`${STATIC_ROOT}/scss/*.scss`, sassProcess)
}

gulp.task('watch', gulp.series(sassProcessWatch))

function cleanProcess () {
  return del([`${STATIC_ROOT}/js/*.min.js`])
}

function minArticleCSS () {
  // min article css
  return gulp.src([
    `${STATIC_ROOT}/js/lib/diff2html/diff2html.min.css`]).
    pipe(cleanCSS()).
    pipe(concat('article.min.css')).
    pipe(gulp.dest(`${STATIC_ROOT}/js/lib/compress/`))
}

function minJS () {
  // min js
  return gulp.src(`${STATIC_ROOT}/js/*.js`).
    pipe(uglify()).
    pipe(rename({suffix: '.min'})).
    pipe(gulp.dest(`${STATIC_ROOT}/js/`))
}

function minUpload () {
  // concat js
  var jsJqueryUpload = [
    `${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/vendor/jquery.ui.widget.js`,
    `${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/jquery.iframe-transport.js`,
    `${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/jquery.fileupload.js`,
    `${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/jquery.fileupload-process.js`,
    `${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/jquery.fileupload-validate.js`]
  return gulp.src(jsJqueryUpload).
    pipe(uglify()).
    pipe(concat('jquery.fileupload.min.js')).
    pipe(gulp.dest(`${STATIC_ROOT}/js/lib/jquery/file-upload-9.10.1/`))
}

function minLibs () {
  var jsCommonLib = [
    `${STATIC_ROOT}/js/lib/jquery/jquery-3.1.0.min.js`,
    `${STATIC_ROOT}/js/lib/md5.js`,
    `${STATIC_ROOT}/js/lib/reconnecting-websocket.min.js`,
    `${STATIC_ROOT}/js/lib/jquery/jquery.bowknot.min.js`,
    `${STATIC_ROOT}/js/lib/ua-parser.min.js`,
    `${STATIC_ROOT}/js/lib/jquery/jquery.hotkeys.js`,
    `${STATIC_ROOT}/js/lib/jquery/jquery.pjax.js`,
    `${STATIC_ROOT}/js/lib/nprogress/nprogress.js`]
  return gulp.src(jsCommonLib).
    pipe(uglify()).
    pipe(concat('libs.min.js')).
    pipe(gulp.dest(`${STATIC_ROOT}/js/lib/compress/`))
}

function minArticleLibs () {
  var jsArticleLib = [
    `${STATIC_ROOT}/js/lib/sound-recorder/SoundRecorder.js`,
    `${STATIC_ROOT}/js/lib/jquery/jquery.qrcode.min.js`,
    `${STATIC_ROOT}/js/lib/aplayer/APlayer.min.js`,
    `${STATIC_ROOT}/js/lib/diff2html/diff2html.min.js`,
    `${STATIC_ROOT}/js/lib/diff2html/diff2html-ui.min.js`,
    `${STATIC_ROOT}/js/lib/diff2html/diff.min.js`]
  return gulp.src(jsArticleLib).
    pipe(uglify()).
    pipe(concat('article-libs.min.js')).
    pipe(gulp.dest(`${STATIC_ROOT}/js/lib/compress/`))
}

gulp.task('default',
  gulp.series(cleanProcess, sassProcess,
    gulp.parallel(minJS, minUpload, minLibs),
    gulp.parallel(minArticleCSS, minArticleLibs)))
