'use strict';

(function(module) {
  module.exports = function(context, name) {
    var ReactBootstrap = context.libs.ReactBootstrap;
    var Tabs = ReactBootstrap.TabbedArea;
    var Tab = ReactBootstrap.TabPane;

    var Config = context.components.Admin.Config;
    var Webhooks = context.components.Admin.Webhooks;

    context.createComponent(name, 'Main', {
      getInitialState: function() {
        return {
          activeKey: 1
        };
      },
      handleSelect: function(key) {
        this.setState({activeKey: key});
      },
      render: function() {
        return (
          <div className="row">
            <div className="col-lg-12">
              <Tabs activeKey={this.state.activeKey} onSelect={this.handleSelect}>
                <Tab eventKey={1} tab="Config">
                  <Config></Config>
                </Tab>
                <Tab eventKey={2} tab="Webhooks">
                  <Webhooks></Webhooks>
                </Tab>
              </Tabs>
            </div>
          </div>
        );
      }
    });

  }

}(module))
