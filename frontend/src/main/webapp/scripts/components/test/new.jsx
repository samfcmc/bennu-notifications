'use strict';

(function(module) {

  module.exports = function(context, name) {
    context.createComponent(name, 'New', {
      render: function() {
        var itemClass = function(count) {
          var name = 'list-group-item';
          return name + (count == 0 ? '' : ' active');
        }
        return (
          <div className="row">
            <ul className="list-group">
              <li className={itemClass(this.props.new)}>
                <span className="badge">{this.props.new}</span>
                New notifications
              </li>
            </ul>
          </div>
        );
      }
    });

  };

}(module));
