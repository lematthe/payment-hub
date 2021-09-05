import React from 'react'


const Txs = ({ txs }) => {
    return (
        <div>
            <table class="pf-c-table pf-m-grid-md" role="grid" aria-label="Orphaned Transactions" id="table-basic" >
                <caption>Orphaned Transactions</caption>
                <thead>
                    <tr role="row">
                        <th role="columnheader" scope="col">Transaction ID</th>
                        <th role="columnheader" scope="col">Transaction Type</th>
                        <th role="columnheader" scope="col">Requested Amount</th>
                        <th role="columnheader" scope="col">State</th>
                        <th role="columnheader" scope="col">Country Code</th>
                        <th role="columnheader" scope="col">Institution ID</th>
                    </tr>
                </thead>
                    {txs?.map((tx) => (
                        <tbody role="rowgroup">
                            <tr role="row">
                                <td role="cell" data-label="Transaction ID">{tx.txID}</td>                                
                                <td role="cell" data-label="Transaction Type">{tx.txType}</td>
                                <td role="cell" data-label="Requested Amount">{tx.requestedAmount}</td>
                                <td role="cell" data-label="State">{tx.txState}</td>
                                <td role="cell" data-label="Country Code">{tx.countryCode}</td>
                                <td role="cell" data-label="Institution ID">{tx.institutionID}</td>
                            </tr>
                        </tbody>
                    ))}
                }
            </table>
        </div>

    )
};

export default Txs
