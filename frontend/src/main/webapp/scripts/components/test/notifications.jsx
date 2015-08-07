'use strict';

(function(module) {

  module.exports = function(React, New, List, NotificationsClient) {
    var Notifications = React.createClass({
      getInitialState: function() {
        return {
          list: []
        };
      },
      componentDidMount: function() {
        NotificationsClient.getLastN(10, this.updateList);
      },
      updateList: function(list) {
        this.setState({
          list: list
        });
      },
      render: function() {
        return (
          <div className="row">
            <New></New>
            <List list={this.state.list}></List>
          </div>
        );
      }
    });

    return Notifications;
  }

}(module));
