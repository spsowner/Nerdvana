import * as request from 'superagent';
import ServerActions from '../actions/ServerActions';

const API_ROOT = 'api/v1';

/**
 * Object with utility methods to interact with the API
 * @type {Object}
 */
var ApiUtils = {

    getAllQuestions() {
        // TODO Use API /questions
        var MOCK_DATA = [{
            id: 1,
            name: 'What projects should we do this year?'
        }];

        ServerActions.receiveQuestions(MOCK_DATA);
    },

    createQuestion() {
        // TODO
    }

};

export default ApiUtils;
