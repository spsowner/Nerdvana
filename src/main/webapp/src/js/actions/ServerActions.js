import AppDispatcher from '../dispatcher/AppDispatcher';
import QuestionConstants from '../constants/QuestionConstants';

/**
 * Module delegates server actions to dispatcher
 * @type {Object}
 */
var ServerActions = {

    /**
     * Receive questions
     * @param  {Array} questions
     */
    receiveQuestions(questions) {
        AppDispatcher.dispatch({
            actionType: QuestionConstants.RECEIVE_QUESTIONS,
            questions: questions
        });
    }

};

export default ServerActions;


