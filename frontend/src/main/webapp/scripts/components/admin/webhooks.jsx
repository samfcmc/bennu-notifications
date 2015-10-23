'use strict';

(function(module) {
  module.exports = function(context, name) {
    var ReactBootstrap = context.libs.ReactBootstrap;
    var Button = ReactBootstrap.Button;
    var Glyphicon = ReactBootstrap.Glyphicon;
    var Modal = ReactBootstrap.Modal;
    var Input = ReactBootstrap.Input;
    var NewWebhook = context.components.Admin.NewWebhook;

    context.createComponent(name, 'Webhooks', {
      getInitialState: function() {
        return {
          showModal: false
        };
      },
      showModal: function() {
        this.setState({showModal: true});
      },
      close: function() {
        this.setState({showModal: false});
      },
      render: function() {
        return (
          <div className="col-lg-12">
            <h2>Webhooks configuration</h2>
            <div className="row">
              <div className="col-lg-12">
                <Button bsStyle="success" onClick={this.showModal}>
                  <Glyphicon glyph="plus" />
                  Add webhook
                </Button>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                TODO: List of webhooks
              </div>
            </div>

            <NewWebhook showModal={this.state.showModal} close={this.close}>
            </NewWebhook>

          </div>
        );
      }
    });

  }

}(module))
