import AppDispatcher from '../dispatcher/AppDispatcher';
import {EventEmitter} from 'events';

const CHANGE_EVENT = 'change';

var _questions = {};

var Question = {

    addQuestions(questions) {
        questions.forEach((question) => {
            if (!_questions[question.id]) {
                _questions[question.id] = question;
            }
        });
    },

    get(id) {
        return _questions[id];
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
            var { name, id, actionType } = payload.action;

            switch (actionType) {
                default:
                    // do nothing
            }
        });
    }

    getQuestions() {
        return Question.getAll();
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
