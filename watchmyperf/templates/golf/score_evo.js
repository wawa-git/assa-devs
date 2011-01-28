new Highcharts.Chart({
	chart: {
		renderTo: '{{container}}',
		margin: [80, 100, 60, 100],
		zoomType: 'xy'
	},
	title: {
		text: 'Performance',
		style: {
			margin: '10px 0 0 0' // center it
		}
	},
	subtitle: {
		text: "(évolution)",
		style: {
			margin: '0 0 0 0' // center it
		}
	},
	xAxis: [{
		categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 
			'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	}],
	yAxis: [
	        { // Score moyen
	        	labels: {
	        		formatter: function() {
	        			return this.value;
					},
					style: {
						color: '#89A54E'
					}
	        	},
	        	title: {
	        		text: 'Score (moyenne)',
	        		style: {
	        			color: '#89A54E'
	        		},
	        		margin: 60
	        	}
	        },
	        { // GIR moyen
	        	title: {
	        		text: 'GIR (moyenne)',
	        		margin: 70,
	        		style: {
						color: '#4572A7'
					}
	        	},
	        	labels: {
	        		formatter: function() {
	        			return this.value;
	        		},
	        		style: {
	        			color: '#4572A7'
	        		}
	        	},
	        },
	        { // Fairways touchés moyen
	        	title: {
	        		text: 'Fairways touchés (moyenne)',
	        		margin: 70,
	        		style: {
						color: '#4572A7'
					}
	        	},
	        	labels: {
	        		formatter: function() {
	        			return this.value + "%";
	        		},
	        		style: {
	        			color: '#4572A7'
	        		}
	        	},
	        },
	        { // Put moyen
	        	title: {
	        		text: 'Put/trou (moyenne)',
	        		margin: 70,
	        		style: {
						color: '#4572A7'
					}
	        	},
	        	labels: {
	        		formatter: function() {
	        			return this.value;
	        		},
	        		style: {
	        			color: '#4572A7'
	        		}
	        	},
	        }
	        ],
    tooltip: {
		formatter: function() {
			return '<b>'+ this.series.name +'</b><br/>'+
				this.x +': '+ this.y;
		}
	},
	legend: {
		layout: 'vertical',
		style: {
			left: '120px',
			bottom: 'auto',
			right: 'auto',
			top: '100px'
		},
		backgroundColor: '#FFFFFF'
	},
	series: [
	         {
	        	 name: 'Score moyen',
	        	 color: '#4572A7',
	        	 type: 'spline',
	        	 yAxis: 1,
	        	 data: {{ avg_scores }}		
	         },
	         {
	        	 name: 'GIR moyen',
	        	 color: '#89A54E',
	        	 type: 'spline',
	        	 yAxis: 1,
	        	 data: {{ avg_girs }}
	         },
	         {
	        	 name: 'Fairway touché moyen',
	        	 color: '#89A54E',
	        	 type: 'spline',
	        	 yAxis: 1,
	        	 data: {{ avg_fairways }}
	         },
	         {
	        	 name: 'Put/trou moyen',
	        	 color: '#89A54E',
	        	 type: 'spline',
	        	 yAxis: 1,
	        	 data: {{ avg_puts }}
	         }
	         ]
	});