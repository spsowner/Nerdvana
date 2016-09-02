import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';
import SelectField from 'material-ui/lib/select-field';
import MenuItem from 'material-ui/lib/menus/menu-item';
import QuestionStore from './../../stores/QuestionStore';
import QuestionActions from './../../actions/QuestionActions';

const NewQuestion = React.createClass({

    getInitialState() {
        return this._getStateFromStore();
    },

    componentDidMount: function() {
        QuestionStore.addChangeListener(this._onChange);
    },

    componentWillUnmount: function() {
        QuestionStore.removeChangeListener(this._onChange);
    },

    _onChange() {
        this.setState(this._getStateFromStore());
    },

    _getStateFromStore() {
        var currentQuestion = QuestionStore.getCurrent();

        return {
            question: currentQuestion || { id: '', name: ''},
            questions: QuestionStore.getQuestions(),
            isDisabled: !currentQuestion
        };
    },

    _onInputChange(event) {
        var value = event.target.value;

        this.setState({
            question: value,
            isDisabled: value.length === 0
        });
    },

    _onSelectChange(event, selectedIndex, value) {
        QuestionActions.selectQuestion(value);
    },

    render() {
        const btnStyle = {
            float: 'right',
            marginTop: '20px'
        };

        var defaultQuestions = this.state.questions.map((question) => {
            return (<MenuItem key={question.id}
                        value={question.id}
                        primaryText={question.name} />);
        });

        return (
            <div className="container">
                <header>
                    <h1>Ask A Question</h1>
                </header>
                <TextField
                    hintText="Enter a Question"
                    fullWidth={true}
                    value={this.state.question.name}
                    onChange={this._onInputChange}/>
                <div style={{width: '100%', padding: '20px 0' }}>or</div>
                <SelectField
                    hintText="Select a question to validate. You will be able to modify criteria."
                    autoWidth={true}
                    fullWidth={true}
                    onChange={this._onSelectChange}>
                    {defaultQuestions}
                </SelectField>
                <div style={{width: '100%'}}>
                    <RaisedButton
                        label="Next"
                        primary={true}
                        disabled={this.state.isDisabled}
                        style={btnStyle}
                        linkButton={true}
                        href="/#/criteria" />
                </div>
            </div>
        );
    }

});

export default NewQuestion;
