'use strict';

(function(module) {

  module.exports = function(React, Notification) {
    var List = React.createClass({
      getInitialState: function() {
        return {
          data: [],
          type: this.props.type
        };
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
              <h2>{this.state.type}</h2>
            </div>
            <div className="row">
              <ul className="list-groupt">
                {rows}
              </ul>
            </div>
          </div>
        );
      }
    });

    return List;
  }

}(module));
