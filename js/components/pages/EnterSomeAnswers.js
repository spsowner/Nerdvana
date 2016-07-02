import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';

const EnterSomeAnswers = React.createClass({

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
            <div>
                <div className="container">
                    <header>
                        <h1>Displays Current question</h1>
                    </header>
                    <TextField
                        hintText="Question Selected is displayed here"
                        fullWidth={true}
                        value={this.state.question}
                        onChange={this._onInputChange}/>
                </div>
                 <div className="container">
                    <header>
                        <h1>Displays who asked</h1>
                    </header>
                    <TextField
                        hintText="Username for asker is displayed here"
                        fullWidth={true}
                        value={this.state.question}
                        onChange={this._onInputChange}/>
                </div>
                <div className="container">
                    <header>
                        <h1>Enter Some Answers</h1>
                    </header>
                    <TextField
                        hintText="Enter an answer to the question above"
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
            </div>
        );
    }

});

export default EnterSomeAnswers;
