<div class="container-fluid">
    <div class="row">
        <div id="chart"></div>

        <script>
            $(function () {
                $("#chart").shieldChart(
                    {
                        theme: "light",
                        seriesSettings: {
                            line: {
                                dataPointText: {
                                    enabled: true
                                }
                            }
                        },
                        chartLegend: {
                            align: 'center',
                            borderRadius: 2,
                            borderWidth: 2,
                            verticalAlign: 'top'
                        },
                        exportOptions: {
                            image: false,
                            print: false
                        },
                        axisX: {
                            categoricalValues:
                                [
                                    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
                                ]
                        },
                        axisY: {
                            title: {
                                text: "Count"
                            }
                        },
                        primaryHeader: {
                            text: "Developer's Task's Statistics for ${selectedYear} year"
                        },
                        dataSeries: [
                            {
                                seriesType: 'line',
                                collectionAlias: 'Task',
                                data: [
                                    <#list chartStatisticsHelper.countTask as i>
                                    ${i},
                                    </#list>
                                ]
                            },
                            {
                                seriesType: 'line',
                                collectionAlias: 'Defect',
                                data: [
                                    <#list chartStatisticsHelper.countAllDefect as i>
                                    ${i},
                                    </#list>
                                ]
                            },
                            {
                                seriesType: 'line',
                                collectionAlias: 'Return',
                                data: [
                                    <#list chartStatisticsHelper.countAllReturn as i>
                                    ${i},
                                    </#list>
                                ]
                            },
                            {
                                seriesType: 'line',
                                collectionAlias: 'Re-Return',
                                data: [
                                    <#list chartStatisticsHelper.countAllRevert as i>
                                    ${i},
                                    </#list>
                                ]
                            },
                            {
                                seriesType: 'line',
                                collectionAlias: 'No Defect',
                                data: [
                                    <#list chartStatisticsHelper.countAllNoDefect as i>
                                    ${i},
                                    </#list>
                                ]
                            }
                        ]
                    }
                );
            });
        </script>
    </div>
</div>