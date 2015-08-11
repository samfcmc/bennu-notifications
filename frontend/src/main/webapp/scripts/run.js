'use strict';

(function(require, module) {
  module.exports = function(User, React, Router, ReactBootstrap, moment, jQuery) {
    var NotificationsClient = require('./notifications');

    var context = require('./context')({React, ReactRouter, ReactBootstrap,
      moment, jQuery, NotificationsClient});
    context.user = User;

    var components = require('./components')(context);

    var Routes = require('./routes')(context);

    Router.run(Routes, Router.HashLocation, function(Handler) {
      React.render(
        <Handler/>,
        document.getElementById('content')
      );
    });
  };
}(require, module));
