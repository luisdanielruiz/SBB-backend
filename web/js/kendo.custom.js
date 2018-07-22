// Kendo UI theme for data visualization widgets
// Use as theme: 'newTheme' in configuration options (or change the name)
kendo.dataviz.ui.registerTheme('newTheme', {
    "chart": {
        "title": {
            "color": "#777777"
        },
        "legend": {
            "labels": {
                "color": "#777777"
            }
        },
        "chartArea": {},
        "seriesDefaults": {
            "labels": {
                "color": "#000"
            }
        },
        "axisDefaults": {
            "line": {
                "color": "#c7c7c7"
            },
            "labels": {
                "color": "#777777"
            },
            "minorGridLines": {
                "color": "#c7c7c7"
            },
            "majorGridLines": {
                "color": "#c7c7c7"
            },
            "title": {
                "color": "#777777"
            }
        },
        "seriesColors": [
            "#8ebc00",
            "#309b46",
            "#25a0da",
            "#ff6900",
            "#e61e26",
            "#d8e404"
        ],
        "tooltip": {}
    },
    "gauge": {
        "pointer": {
            "color": "#8ebc00"
        },
        "scale": {
            "rangePlaceholderColor": "#e6e6e6",
            "labels": {
                "color": "#777"
            },
            "minorTicks": {
                "color": "#777"
            },
            "majorTicks": {
                "color": "#777"
            },
            "line": {
                "color": "#777"
            }
        }
    }
});