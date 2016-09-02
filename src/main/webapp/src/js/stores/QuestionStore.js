import {EventEmitter} from 'events';
import AppDispatcher from '../dispatcher/AppDispatcher';
import QuestionConstants from '../constants/QuestionConstants';
import unionBy from 'lodash/unionBy';
import find from 'lodash/find';

const CHANGE_EVENT = 'change';

var _questions = [];

var Question = {

    currentId: null,

    addQuestions(questions) {
        _questions = unionBy(_questions, questions, 'id');
    },

    get(id) {
        return find(_questions, ['id', id]);
    },

    getAll() {
        return _questions;
    },

    create() {
        // TODO
    },

    update() {
        // TODO
    },

    destroy() {
        // TODO
    }

};

class QuestionStore extends EventEmitter {

    constructor() {
        super();

        AppDispatcher.register((payload) => {

            switch (payload.actionType) {
                case QuestionConstants.RECEIVE_QUESTIONS:
                    Question.addQuestions(payload.questions);
                    this.emitChange();
                    break;
                case QuestionConstants.SELECT_QUESTION:
                    Question.currentId = payload.id;
                    this.emitChange();
                default:
                    // do nothing
            }
        });
    }

    getQuestions() {
        return Question.getAll();
    }

    getQuestion(id) {
        return Question.get(id);
    }

    getCurrent() {
        return Question.get(Question.currentId);
    }

    emitChange() {
        this.emit(CHANGE_EVENT);
    }

    addChangeListener(cb) {
        this.on(CHANGE_EVENT, cb);
    }

    removeChangeListener(cb) {
        this.removeListener(CHANGE_EVENT, cb);
    }
}

export default new QuestionStore();
