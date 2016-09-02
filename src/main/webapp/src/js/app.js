import injectTapEventPlugin from 'react-tap-event-plugin';
import React from 'react';
import ReactDom from 'react-dom';
import {Router, Route, IndexRoute, useRouterHistory} from 'react-router';
import {createHashHistory} from 'history';
import AppBar from 'material-ui/lib/app-bar';
import ApiUtils from './utils/ApiUtils';
import NewQuestion from './components/pages/NewQuestion';
import EnterSomeAnswers from './components/pages/EnterSomeAnswers';
import Criteria from './components/pages/Criteria';
import User from './components/pages/User';
import Group from './components/pages/Group';
import Question from './components/pages/Question';
import SignUp from './components/pages/SignUp';

// Needed for onTouchTap
// Can go away when react 1.0 release
injectTapEventPlugin();

ApiUtils.getAllQuestions();

const App = React.createClass({

    propTypes: {
        children: React.PropTypes.node
    },

    getInitialState() {
        return {
        };
    },

    componentWillMount() {

    },

    componentDidMount() {

    },

    componentWillUnmount() {

    },

    render() {

        return (
            <div>
                <AppBar title="Nerdvana" />
                {this.props.children}
            </div>
        );
    }
});

ReactDom.render((
    <Router
        history={useRouterHistory(createHashHistory)({queryKey: false})}
        onUpdate={() => window.scrollTo(0, 0)}
    >
        <Route path="/" component={App}>
            <IndexRoute component={NewQuestion}/>
            <Route path="/criteria" component={Criteria}/>
            <Route path="/answer" component={EnterSomeAnswers}/>
            <Route path="/user/:id" component={User}/>
            <Route path="/group/:id" component={Group}/>
            <Route path="/question/:id" component={Question}/>
            <Route path="/sign-up" component={SignUp}/>
            <Route path="*" component={Criteria}/>
        </Route>
    </Router>
), document.getElementById('app'));
