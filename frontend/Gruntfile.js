module.exports = function(grunt) {
    require('load-grunt-tasks')(grunt);

    grunt.initConfig({
        srcPath: 'src/main/webapp',
        targetPath: '<%= grunt.config.get("targetPath") %>',
        scriptsPath: '<%= srcPath %>/scripts',
        index: 'index.html',
        indexPath: '<%= srcPath %>/<%= index %>',
        styleMain: 'style',
        styleSrcPath: '<%= srcPath %>/styles',
        lessMainPath: '<%= styleSrcPath %>/<%= styleMain %>.less',
        styleTargetPath: '<%= targetPath %>/styles/<%= styleMain %>.css',
        targetJSPath: '<%= targetPath %>/js',
        mainScript: 'main.js',
        mainScriptPath: '<%= scriptsPath %>/<%= mainScript %>',
        mainScriptTargetPath: '<%= targetJSPath %>/<%= mainScript %>',
        vendorsScript: 'vendors.js',
        vendorsScriptPath: '<%= scriptsPath %>/<%= vendorsScript %>',
        vendorsScriptTargetPath: '<%= targetJSPath %>/<%= vendorsScript %>',
        debug: '<%= grunt.config.get("debug") %>',
        buildPath: 'build',
        distPath: 'dist',
        bowerComponents: 'bower_components',
        bowerTargetPath: '<%= targetPath %>/<%= bowerComponents %>',
        bowerBuildPath: '<%= srcPath %>/<%= bowerComponents %>',
        config: {
            dev: {
                options: {
                    variables: {
                        debug: true,
                        targetPath: '<%= buildPath %>'
                    }
                }
            },
            dist: {
                options: {
                    variables: {
                        debug: false,
                        targetPath: '<%= distPath %>'
                    }
                }
            }
        },
        bower: {
            target: {
                options: {
                    targetDir: '<%= bowerTargetPath %>',
                }
            }
        },
        browserify: {
            vendors: {
                src: ['<%= vendorsScriptPath %>'],
                dest: '<%= vendorsScriptTargetPath %>',
                options: {
                    transform: ['debowerify']
                }
            },
            main: {
                src: ['<%= mainScriptPath %>'],
                dest: '<%= mainScriptTargetPath %>',
                options: {
                    transform: ['reactify'],
                    browserifyOptions: {
                        debug: '<%= debug %>'
                    }
                }
            }
        },
        less: {
            style: {
                files: {
                    '<%= bowerBuildPath %>/bootstrap/bootstrap.css': '<%= bowerComponents %>/bootstrap/less/bootstrap.less',
                    '<%= styleTargetPath %>': '<%= lessMainPath %>'
                }
            }
        },
        connect: {
            server: {
                options: {
                    base: '<%= targetPath %>',
                    port: 9000,
                    hostname: 'localhost',
                    middleware: function(connect, options, defaultMiddleware) {
                        var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;
                        return [proxySnippet].concat(defaultMiddleware);
                    }
                },
                proxies: {
                    context: ['/api', '/login', '/logout', '/css/login.css', '/bennu-portal'],
                    host: 'localhost',
                    port: 8080
                }
            }
        },
        clean: {
            build: {
                src: ['<%= bowerComponents %>',
                    'bowerBuildPath',
                    '<%= buildPath %>',
                    '<%= distPath %>']
            }
        },
        copy: {
            index: {
                files: [
                    {expand: true,
                    cwd: '<%= srcPath %>',
                    src: ['<%= index %>'],
                    dest: '<%= targetPath %>/',
                    filter: 'isFile'},
                ]
            }
        },
        uglify: {
            vendors: {
                files: {
                    '<%= vendorsScriptTargetPath %>': ['<%= vendorsScriptPath %>']
                }
            },
            main: {
                files: {
                    '<%= mainScriptTargetPath %>': ['<%= mainScriptPath %>']
                }
            }
        },
        watch: {
            main: {
                files: ['<%= mainScriptPath %>', '<%= scriptsPath %>/**/*.js'],
                tasks: ['config:dev', 'browserify:main']
            },
            vendors: {
                files: ['<%= vendorsScriptPath %>'],
                tasks: ['config:dev', 'browserify:vendors']
            },
            index: {
                files: ['<%= indexPath %>'],
                tasks: ['config:dev', 'copy:index']
            },
            styles: {
                files: ['<%= styleSrcPath %>/**/*.less'],
                tasks: ['config:dev', 'less']
            }
        }
    });

    grunt.registerTask('common', ['bower', 'browserify', 'copy', 'less']);
    grunt.registerTask('dev', ['config:dev', 'common', 'connect', 'watch']);
    grunt.registerTask('dist', ['config:dist', 'common', 'uglify']);
    grunt.registerTask('default', ['dev']);
};
