'use strict';

(function(module) {

  const POLLING_SECONDS = 10;

  module.exports = function(React, New, List, NotificationsClient, ReactBootstrap) {
    var Notifications = React.createClass({
      getInitialState: function() {
        return {
          list: [],
          new: 0,
          alertVisible: false
        };
      },
      componentDidMount: function() {
        NotificationsClient.getLastN(10, this.updateList);
      },
      updateList: function(list) {
        list.sort(function(a, b) {
          return b.timestamp - a.timestamp;
        });
        this.setState({
          list: list
        }, this.startPolling);
      },
      startPolling: function() {
        var last = this.state.list[0];
        NotificationsClient.poll(POLLING_SECONDS, this.addAll);
      },
      addAll: function(newList) {
        var list = this.state.list;
        var count = newList.length;
        for(var i = 0; i < newList.length; i++) {
          var item = newList[i];
          list.push(item);
        }
        list.sort(function(a, b) {
          return b.timestamp - a.timestamp;
        });
        var alertVisible = newList.length > 0 ? true : false;

        this.setState({
          list: list,
          new: count,
          alertVisible: alertVisible
        });
      },
      handleAlertDismiss: function() {
        this.setState({
          alertVisible: false
        });
      },
      render: function() {
        var Alert = ReactBootstrap.Alert;
        var NewAlert = (
          <div></div>
        );
        if(this.state.alertVisible) {
          NewAlert = (
            <Alert bsStyle="info" onDismiss={this.handleAlertDismiss}
            dismissAfter={10000}>
              You got {this.state.new} new notifications
            </Alert>
          );
        }
        return (
          <div className="row">
            {NewAlert}
            <New new={this.state.new}></New>
            <List list={this.state.list}></List>
          </div>
        );
      }
    });

    return Notifications;
  }

}(module));
