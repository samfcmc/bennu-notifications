'use strict';

(function(module) {

  module.exports = function(context, name) {
    var libs = context.libs;
    var NotificationsClient = libs.NotificationsClient;
    var moment = libs.moment;
    var ReactBootstrap = libs.ReactBootstrap;

    context.createComponent(name, 'Notification', {
      getInitialState: function() {
        return {
          notification: this.props.notification
        };
      },
      afterRead: function(response) {
        var onRead = this.props.onRead;
        if(onRead) {
          onRead();
        }
        this.setState({
          notification: response
        });
      },
      read: function() {
        NotificationsClient.read(this.state.notification.id, this.afterRead);

      },
      readIcon: function() {
        return this.state.notification.read ? 'ok' : 'remove'
      },
      fromNow: function() {
        return moment(this.state.notification.timestamp).fromNow();
      },
      render: function() {
        var Button = ReactBootstrap.Button;
        var Modal = ReactBootstrap.Modal;
        var Glyphicon = ReactBootstrap.Glyphicon;
        var self = this;
        var ReadButton = (
          <div></div>
        );
        if(!this.state.notification.read) {
          ReadButton = (
            <div className="row">
              <Button
              className="pull-right"
              bsStyle='default'
              onClick={this.read}>
                Read
              </Button>
            </div>
          );
        }
        return (
          <li className="list-group-item">
            <div className="row">
              {this.fromNow()}
            </div>
            <div className="row">
              Read: <Glyphicon glyph={this.readIcon()} />
            </div>
            {ReadButton}
          </li>
        );
      }
    });

  };

}(module));
