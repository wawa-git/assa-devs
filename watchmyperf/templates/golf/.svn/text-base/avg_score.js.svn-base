new Highcharts.Chart({
   chart: {
      renderTo: '{{container}}',
      margin: [30, 70, 0, 0]
   },
   title: {
      text: 'Score moyen'
   },
   plotArea: {
      shadow: null,
      borderWidth: null,
      backgroundColor: null
   },
   tooltip: {
      formatter: function() {
         return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
      }
   },
   plotOptions: {
      pie: {
         allowPointSelect: true,
         dataLabels: {
            enabled: true,
            formatter: function() {
               if (this.y > 5) return this.point.name;
            },
            color: 'white',
            style: {
               font: '13px Trebuchet MS, Verdana, sans-serif'
            }
         }
      }
   },
   legend: {
      layout: 'vertical',
      style: {
         left: 'auto',
         bottom: 'auto',
         right: '0px',
         top: '100px'
      }
   },
      series: [{
      type: 'pie',
      name: 'Score moyen',
      data: [
         {{ data }}
      ]
   }]
});