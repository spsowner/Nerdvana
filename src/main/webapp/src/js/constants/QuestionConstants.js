/**
 * Constants object to save
 * action names throughout app.
 * @type {Object}
 */
const QuestionConstants = {

    /**
     * Create question action type
     * @type {String}
     */
    CREATE: 'QUESTION_CREATE',

    /**
     * Update question action type
     * @type {String}
     */
    UPDATE: 'QUESTION_UPDATE',

    /**
     * Destroy question action type
     * @type {String}
     */
    DESTROY: 'QUESTION_DESTROY',

    /**
     * Select question
     * @type {String}
     */
    SELECT_QUESTION: 'SELECT_QUESTION',

    /**
     * Receive questions from server
     * @type {String}
     */
    RECEIVE_QUESTIONS: 'RECEIVE_QUESTIONS'
};

export default QuestionConstants;
