import React from 'react';
import {
    Tabs, Tab, TabTitleText, Brand, Page, PageHeader, PageHeaderTools,
    PageHeaderToolsGroup,
    PageHeaderToolsItem
} from '@patternfly/react-core';
import rhLogo from '../images/Logo-RedHat-D-Color-RGB.png';
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
            switch (tabIndex) {
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
        const { activeTabKey } = this.state;

        const headerTools = (
            <PageHeaderTools>
                <PageHeaderToolsGroup
                    visibility={{
                        default: 'hidden',
                        lg: 'visible'
                    }} /** the settings and help icon buttons are only visible on desktop sizes and replaced by a kebab dropdown for other sizes */
                >
                    <PageHeaderToolsItem>
                    </PageHeaderToolsItem>
                </PageHeaderToolsGroup>
            </PageHeaderTools>
                        );
        const Header = (
            <PageHeader logo={<Brand src={rhLogo} alt="Patternfly Logo" />} headerTools={headerTools} >Integration Demo </PageHeader>
        );

        return (
            <div>
                <Page header={Header}>
                <Tabs activeKey={activeTabKey} onSelect={this.handleTabClick}>
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
                </Page>
            </div>
        );
    }
}

export default MainPage