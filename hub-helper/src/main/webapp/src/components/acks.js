import React from 'react'


const Acks = ({ acks }) => {
    return (
        <div>
            <table class="pf-c-table pf-m-grid-md" role="grid" aria-label="Orphaned Acknowledgements" id="table-basic" >
                <caption>Orphaned Acknowledgements</caption>
                <thead>
                    <tr role="row">
                        <th role="columnheader" scope="col">Acknowledgment ID</th>
                        <th role="columnheader" scope="col">Transaction ID</th>
                        <th role="columnheader" scope="col">Confirmed Amount</th>
                        <th role="columnheader" scope="col">Notes</th>
                        <th role="columnheader" scope="col">Status</th>
                    </tr>
                </thead>
                    {acks?.map((ack) => (
                        <tbody role="rowgroup">
                            <tr role="row">
                                <td role="cell" data-label="Acknowledgement ID">{ack.ackID}</td>
                                <td role="cell" data-label="Transaction ID">{ack.txID}</td>
                                <td role="cell" data-label="Confirmed Amount">{ack.confirmedAmount}</td>
                                <td role="cell" data-label="Status">{ack.ackNotes}</td>
                                <td role="cell" data-label="Notes">{ack.ackStatus}</td>
                            </tr>
                        </tbody>
                    ))}
                }
            </table>
        </div>

    )
};

export default Acks
