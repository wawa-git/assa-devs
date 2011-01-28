$("#{{container}}").html(
		"avg score: {{avg_rscore|force_sign}} ({{avg_score}}) <br />" +
		"avg gir: {{avg_gir}}% <br />" +
		"avg fairway: {{avg_fairway}}% <br />" +
		"avg put: {{avg_put}} <br />"
		);
