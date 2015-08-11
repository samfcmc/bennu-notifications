'use strict';

(function(require, module) {
  module.exports = function(User, React, Router, ReactBootstrap, moment, jQuery) {
    var NotificationsClient = require('./notifications');
    var components = require('./components')(React, Router, NotificationsClient,
      ReactBootstrap, moment, User);

    var Routes = require('./routes')(Router, components);

    Router.run(Routes, Router.HashLocation, function(Handler) {
      React.render(
        <Handler/>,
        document.getElementById('content')
      );
    });
  };
}(require, module));
