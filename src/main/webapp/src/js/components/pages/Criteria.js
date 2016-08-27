import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';

const Criteria = React.createClass({

    getInitialState() {
        return {
            question: '',
            isDisabled: true
        };
    },

    render() {
        const btnStyle = {
            float: 'right',
            marginTop: '20px'
        };

        return (
            <div className="container">
                <header>
                    <h1>Create Some Criteria</h1>
                </header>
                <div style={{width: '100%'}}>
                    <div>TODO</div>
                    <RaisedButton
                        label="Next"
                        primary={true}
                        disabled={this.state.isDisabled}
                        style={btnStyle} />
                </div>
            </div>
        );
    }

});

export default Criteria;
