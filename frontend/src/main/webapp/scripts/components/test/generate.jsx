'use strict';

(function(module) {

  module.exports = function(context, name) {
    var libs = context.libs;
    var NotificationsClient = libs.NotificationsClient;
    var ReactBootstrap = libs.ReactBootstrap;
    var user = context.user;

    context.createComponent(name, 'Generate', {
      getInitialState: function() {
        return {
          alertVisible: false
        };
      },
      createTestNotification: function() {
        var usernames = [user.username];
        var payload = {test: 'testing...'};
        NotificationsClient.create(usernames, payload, this.afterCreate);
      },
      afterCreate: function() {
        this.setState({
          alertVisible: true
        });
      },
      handleAlertDismiss: function() {
        this.setState({
          alertVisible: false
        });
      },
      render: function() {
        var Alert = ReactBootstrap.Alert;
        var CreatedAlert = (
          <div></div>
        );
        if(this.state.alertVisible) {
          CreatedAlert = (
            <Alert bsStyle="success" onDismiss={this.handleAlertDismiss} dismissAfter={3000}>
              Notification created successfully
            </Alert>
          )
        }
        return (
          <div className="row">
            <button type="button"
            className="btn btn-default"
            onClick={this.createTestNotification}>
              <span className="glyphicon glyphicon-bell"></span>
              Generate a new notification
            </button>
            {CreatedAlert}
          </div>
        );
      }
    });

  };

}(module));
