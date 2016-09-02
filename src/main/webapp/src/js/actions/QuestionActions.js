import AppDispatcher from '../dispatcher/AppDispatcher';
import QuestionConstants from '../constants/QuestionConstants';

/**
 * Object to delegate
 * global actions to the main dispatcher.
 * @type {Object}
 */
var QuestionActions = {

    /**
     * Create a new question.
     * @param  {String} name
     */
    create(name) {
        AppDispatcher.dispatch({
            actionType: QuestionConstants.CREATE,
            name: name
        });
    },

    /**
     * Update a question.
     * @param  {String} id
     * @param  {String} name
     */
    update(id, name) {
        AppDispatcher.dispatch({
            actionType: QuestionConstants.UPDATE,
            name: name,
            id: id
        });
    },

    /**
     * Destroy a question.
     * @param  {String} id
     */
    destroy(id) {
        AppDispatcher.dispatch({
            actionType: QuestionConstants.DESTROY,
            id: id
        });
    },

    /**
     * Select question from dropdown.
     * @param {String} id
     */
    selectQuestion(id) {
        AppDispatcher.dispatch({
            actionType: QuestionConstants.SELECT_QUESTION,
            id: id
        });
    }

};

export default QuestionActions;
