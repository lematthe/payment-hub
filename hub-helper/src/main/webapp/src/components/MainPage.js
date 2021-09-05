import React from 'react';
import { Tabs, Tab, TabTitleText, Checkbox } from '@patternfly/react-core';
import Acks from './acks'
import Txs from './txs'
import Processed from './processed'

class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            activeTabKey: 0,
            isBox: false,
            acks: [],
            txs: [],
            processed: []
        };
        // Toggle currently active tab
        this.handleTabClick = (event, tabIndex) => {
            this.setState({
                activeTabKey: tabIndex
            });
            switch(tabIndex) {
                case 0:
                    fetch('/ack')
                    .then(res => res.json())
                    .then((data) => {
                        this.setState({ acks: data })
                    })
                    .catch(console.log)

                    break;
                case 1:
                    fetch('/tx')
                    .then(res => res.json())
                    .then((data) => {
                        this.setState({ txs: data })
                    })
                    .catch(console.log)

                    break;
                case 2:
                    fetch('/processed')
                    .then(res => res.json())
                    .then((data) => {
                        this.setState({ processed: data })
                    })
                    .catch(console.log)

                    break;
            }
        };
    }

    render() {
        const { activeTabKey, isBox } = this.state;

        return (
            <div>
                <Tabs activeKey={activeTabKey} onSelect={this.handleTabClick} isBox={isBox}>
                    <Tab eventKey={0} title={<TabTitleText>Orphaned Acknowledgements</TabTitleText>}>
                        <Acks acks={this.state.acks} />
                    </Tab>
                    <Tab eventKey={1} title={<TabTitleText>Orphaned Transactions</TabTitleText>}>
                        <Txs txs={this.state.txs} />
                    </Tab>
                    <Tab eventKey={2} title={<TabTitleText>Processed Transactions</TabTitleText>}>
                        <Processed processed={this.state.processed} />
                    </Tab>
                </Tabs>
            </div>
        );
    }
}

export default MainPage