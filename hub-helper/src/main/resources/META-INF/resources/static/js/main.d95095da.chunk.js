(this.webpackJsonpwebapp=this.webpackJsonpwebapp||[]).push([[0],{12:function(e,t,c){},14:function(e,t,c){},16:function(e,t,c){"use strict";c.r(t);var n=c(1),l=c.n(n),r=c(3),o=c.n(r),a=(c(12),c(13),c(4)),s=c(5),i=c(7),d=c(6),j=(c.p,c(14),c(0)),h=function(e){var t=e.acks;return Object(j.jsxs)("div",{children:[Object(j.jsx)("center",{children:Object(j.jsx)("h1",{children:"Transaction Hub"})}),Object(j.jsxs)("table",{class:"pf-c-table pf-m-grid-md",role:"grid","aria-label":"Orphaned Acknowledgements",id:"table-basic",children:[Object(j.jsx)("caption",{children:"Orphaned Acknowledgements"}),Object(j.jsx)("thead",{children:Object(j.jsxs)("tr",{role:"row",children:[Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Acknowledgment ID"}),Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Transaction ID"}),Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Confirmed Amount"}),Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Notes"}),Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Status"})]})}),null===t||void 0===t?void 0:t.map((function(e){return Object(j.jsx)("tbody",{role:"rowgroup",children:Object(j.jsxs)("tr",{role:"row",children:[Object(j.jsx)("td",{role:"cell","data-label":"Acknowledgement ID",children:e.ackID}),Object(j.jsx)("td",{role:"cell","data-label":"Transaction ID",children:e.txID}),Object(j.jsx)("td",{role:"cell","data-label":"Confirmed Amount",children:e.confirmedAmount}),Object(j.jsx)("td",{role:"cell","data-label":"Status",children:e.ackNotes}),Object(j.jsx)("td",{role:"cell","data-label":"Notes",children:e.ackStatus})]})})})),"}"]})]})},b=function(e){Object(i.a)(c,e);var t=Object(d.a)(c);function c(){var e;Object(a.a)(this,c);for(var n=arguments.length,l=new Array(n),r=0;r<n;r++)l[r]=arguments[r];return(e=t.call.apply(t,[this].concat(l))).state={acks:[]},e}return Object(s.a)(c,[{key:"componentDidMount",value:function(){var e=this;fetch("/ack").then((function(e){return e.json()})).then((function(t){e.setState({acks:t})})).catch(console.log)}},{key:"render",value:function(){return Object(j.jsxs)("div",{children:[Object(j.jsxs)("div",{children:["Number of objects : ",this.state.acks.length]}),Object(j.jsx)(h,{acks:this.state.acks})]})}}]),c}(n.Component),u=function(e){e&&e instanceof Function&&c.e(3).then(c.bind(null,17)).then((function(t){var c=t.getCLS,n=t.getFID,l=t.getFCP,r=t.getLCP,o=t.getTTFB;c(e),n(e),l(e),r(e),o(e)}))};o.a.render(Object(j.jsx)(l.a.StrictMode,{children:Object(j.jsx)(b,{})}),document.getElementById("root")),u()}},[[16,1,2]]]);
//# sourceMappingURL=main.d95095da.chunk.js.map