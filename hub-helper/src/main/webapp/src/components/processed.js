import React from 'react'


const Processed = ({ processed }) => {
    return (
        <div>
            <table class="pf-c-table pf-m-grid-md" role="grid" aria-label="Processed Transactions" id="table-basic" >
                <caption>Processed Transactions</caption>
                <thead>
                    <tr role="row">
                        <th role="columnheader" scope="col">Transaction ID</th>
                        <th role="columnheader" scope="col">Transaction Type</th>
                        <th role="columnheader" scope="col">Acknowledgement ID</th>
                        <th role="columnheader" scope="col">Requested Amount</th>
                        <th role="columnheader" scope="col">State</th>
                        <th role="columnheader" scope="col">Country Code</th>
                        <th role="columnheader" scope="col">Institution ID</th>                 
                        <th role="columnheader" scope="col">Ack Notes</th>                        
                        <th role="columnheader" scope="col">Ack Status</th>
                    </tr>
                </thead>
                    {processed?.map((psd) => (
                        <tbody role="rowgroup">
                            <tr role="row">
                                <td role="cell" data-label="Transaction ID">{psd.txid}</td>
                                <td role="cell" data-label="Transaction Type">{psd.txType}</td>                                
                                <td role="cell" data-label="Acknowledgement ID">{psd.ackid}</td>
                                <td role="cell" data-label="Requested Amount">{psd.requestedAmount}</td>
                                <td role="cell" data-label="State">{psd.txState}</td>
                                <td role="cell" data-label="Country Code">{psd.countryCode}</td>
                                <td role="cell" data-label="Institution ID">{psd.institutionID}</td>
                                <td role="cell" data-label="Notes">{psd.ackNotes}</td>
                                <td role="cell" data-label="Ack Status">{psd.ackStatus}</td>
                            </tr>
                        </tbody>
                    ))}
                }
            </table>
        </div>

    )
};

export default Processed
