import injectTapEventPlugin from 'react-tap-event-plugin';
import React from 'react';
import ReactDom from 'react-dom';
import {Router, Route, IndexRoute, useRouterHistory} from 'react-router';
import {createHashHistory} from 'history';
import AppBar from 'material-ui/lib/app-bar';
import NewQuestion from './components/pages/NewQuestion';

// Needed for onTouchTap
// Can go away when react 1.0 release
injectTapEventPlugin();

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
        </Route>
    </Router>
), document.getElementById('app'));
