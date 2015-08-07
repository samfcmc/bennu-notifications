'use strict';

(function(module) {

  module.exports = function(React, ReactBootstrap, NotificationsClient, moment) {
    var Notification = React.createClass({
      getInitialState: function() {
        return {
          showModal: false,
          notification: this.props.notification
        };
      },
      afterRead: function(response) {
        this.setState({
          notification: response
        });
      },
      read: function() {
        NotificationsClient.read(this.state.notification.id, this.afterRead);
      },
      close: function() {
        this.setState({showModal: false});
      },
      readIcon: function() {
        return this.state.notification.read ? 'ok' : 'remove'
      },
      fromNow: function() {
        return moment(this.state.notification.timestamp).fromNow();
      },
      render: function() {
        var read = this.state.notification.read ? 'True' : 'False'
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

    return Notification;
  };

}(module));
