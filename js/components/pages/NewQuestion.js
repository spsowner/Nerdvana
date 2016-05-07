import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';

const NewQuestion = React.createClass({

    getInitialState() {
        return {
            question: '',
            isDisabled: true
        };
    },

    _onInputChange(event) {
        var value = event.target.value;

        this.setState({
            question: value,
            isDisabled: value.length === 0
        });
    },

    render() {
        const btnStyle = {
            float: 'right',
            marginTop: '20px'
        };

        return (
            <div className="container">
                <header>
                    <h1>Ask A Question</h1>
                </header>
                <TextField
                    hintText="Enter a Question"
                    fullWidth={true}
                    value={this.state.question}
                    onChange={this._onInputChange}/>
                <div style={{width: '100%'}}>
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

export default NewQuestion;
