module.exports = function(grunt) {
    grunt.loadNpmTasks('grunt-bower-task');

    grunt.initConfig({
        bower: {
            install: {
                options: {
                    targetDir: 'src/main/webapp/bower_components',
                    cleanBowerDir: false
                }
            }
        }
    });
    grunt.registerTask('default', ['bower:install']);
};
