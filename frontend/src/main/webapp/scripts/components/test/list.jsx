'use strict';

(function(module) {

  module.exports = function(React, Notification) {
    var List = React.createClass({
      getInitialState: function() {
        return {
          data: [],
          type: this.props.type,
          source: this.props.source
        };
      },
      componentDidMount: function() {
        if(this.state.source) {
          this.state.source(this.updateList);
        }
      },
      updateList: function(response) {
        this.setState({
          data: response
        });
      },
      render: function() {
        var rows = [];
        var data = this.state.data;
        for(var i = 0; i < data.length; i++) {
          var row = data[i];
          rows.push(<Notification data={row}/>);
        }
        return (
          <div className="row">
            <div className="row">
              <h2>Notifications</h2>
            </div>
            <div className="row">
              <ul className="list-groupt">
                {data.map(function(item) {
                  return (
                    <Notification notification={item} key={item.id}/>
                  );
                })}
              </ul>
            </div>
          </div>
        );
      }
    });

    return List;
  }

}(module));
