import moment from "moment";

function RewardsTable(props) {
	if(!props.rewards.length) {
		return null;
	}
  return (
    <div className="Rewards-Table">
			<table className="table table-bordered">
				<thead>
					<tr class="table-secondary">
						<th>
							Customer Transaction Id
						</th>
						<th>
							Transaction Amount
						</th>
						<th>
							Reward Points Earned
						</th>
						<th>
							Transaction Time
						</th>
					</tr>
				</thead>
				<tbody>
					{props.rewards.map(reward => (
						<tr key={reward.id}>
							<td>{reward.id}</td>
							<td>{reward.amount}</td>
							<td>{reward.rewardPoints}</td>
							<td>{moment(reward.postedTime).format("MM/DD/YYYY HH:mm:ss")}</td>
						</tr>
					))}
				</tbody>
				<tfoot>
					<tr>
						<th colSpan="2" class="table-secondary">Total:</th>
						<th class="table-secondary">{props.totalRewards}</th>
					</tr>
				</tfoot>
			</table>
    </div>
  );
}

export default RewardsTable;
