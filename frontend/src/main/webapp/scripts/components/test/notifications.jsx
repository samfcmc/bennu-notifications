'use strict';

(function(module) {

  const POLLING_SECONDS = 10;

  module.exports = function(context, name) {
    var libs = context.libs;
    var NotificationsClient = libs.NotificationsClient;
    var components = context.components.Test;
    var New = components.New;
    var List = components.List;

    context.createComponent(name, 'Notifications', {
      getInitialState: function() {
        return {
          list: [],
          new: 0
        };
      },
      componentDidMount: function() {
        NotificationsClient.getLastN(10, this.updateList);
      },
      componentWillUnmount: function() {
        this.stopPolling();
      },
      updateList: function(list) {
        list.sort(function(a, b) {
          return b.timestamp - a.timestamp;
        });

        var unread = 0;

        for(var i in list) {
          var item = list[i];
          if(!item.read) {
            unread++;
          }
        }

        this.setState({
          list: list,
          new: unread
        }, this.startPolling);
      },
      startPolling: function() {
        NotificationsClient.poll(POLLING_SECONDS, this.addAll);
      },
      stopPolling: function() {
        NotificationsClient.stopPolling();
      },
      addAll: function(newList) {
        var list = this.state.list;
        var count = this.state.new + newList.length;
        for(var i = 0; i < newList.length; i++) {
          var item = newList[i];
          list.push(item);
        }
        list.sort(function(a, b) {
          return b.timestamp - a.timestamp;
        });
        this.setState({
          list: list,
          new: count
        });
      },
      onRead: function() {
        var count = this.state.new - 1;
        this.setState({
          new: count
        });
      },
      render: function() {
        return (
          <div className="row">
            <New new={this.state.new}></New>
            <List list={this.state.list} onRead={this.onRead}></List>
          </div>
        );
      }
    });

  }

}(module));
