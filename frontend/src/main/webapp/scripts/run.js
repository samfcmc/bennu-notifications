'use strict';

(function(require, module) {
  module.exports = function(User, React, Router, ReactBootstrap, moment, jQuery) {
    var NotificationsClient = require('./notifications');

    var context = require('./context')(
      {
        React: React,
        ReactRouter: ReactRouter,
        ReactBootstrap: ReactBootstrap,
        moment: moment,
        jQuery: jQuery,
        NotificationsClient: NotificationsClient
      }
    );
    context.user = User;

    require('./components')(context);

    require('./routes')(context);

    Router.run(context.Routes, Router.HashLocation, function(Handler) {
      React.render(
        <Handler/>,
        document.getElementById('content')
      );
    });
  };
}(require, module));
