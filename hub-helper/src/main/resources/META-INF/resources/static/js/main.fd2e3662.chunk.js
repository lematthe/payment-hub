(this.webpackJsonpwebapp=this.webpackJsonpwebapp||[]).push([[0],{12:function(e,t,n){},14:function(e,t,n){},16:function(e,t,n){"use strict";n.r(t);var c=n(1),r=n.n(c),a=n(3),o=n.n(a),s=(n(12),n(13),n(4)),i=n(5),l=n(7),d=n(6),j=(n.p,n(14),n(0)),h=function e(t){t.acks;return Object(j.jsxs)("div",{children:[Object(j.jsx)("center",{children:Object(j.jsx)("h1",{children:"Transaction Hub"})}),Object(j.jsxs)("table",{class:"pf-c-table pf-m-grid-md",role:"grid","aria-label":"Orphaned Acknowledgements",id:"table-basic",children:[Object(j.jsx)("caption",{children:"Orphaned Acknowledgements"}),Object(j.jsx)("thead",{children:Object(j.jsx)("tr",{role:"row",children:Object(j.jsx)("th",{role:"columnheader",scope:"col",children:"Transaction ID"})})}),e.length?e:e.map((function(e){return Object(j.jsx)("tbody",{role:"rowgroup",children:Object(j.jsx)("tr",{role:"row",children:Object(j.jsx)("td",{role:"cell","data-label":"Transaction ID",children:e.txID})})})}))]})]})},b=function(e){Object(l.a)(n,e);var t=Object(d.a)(n);function n(){var e;Object(s.a)(this,n);for(var c=arguments.length,r=new Array(c),a=0;a<c;a++)r[a]=arguments[a];return(e=t.call.apply(t,[this].concat(r))).state={acks:[]},e}return Object(i.a)(n,[{key:"componentDidMount",value:function(){var e=this;fetch("/ack").then((function(e){return e.json()})).then((function(t){e.setState({acks:t}),console.log("set data "+Array.from(t))})).catch(console.log)}},{key:"render",value:function(){return Object(j.jsx)("div",{children:Object(j.jsx)(h,{acks:this.state.acks})})}}]),n}(c.Component),u=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,17)).then((function(t){var n=t.getCLS,c=t.getFID,r=t.getFCP,a=t.getLCP,o=t.getTTFB;n(e),c(e),r(e),a(e),o(e)}))};o.a.render(Object(j.jsx)(r.a.StrictMode,{children:Object(j.jsx)(b,{})}),document.getElementById("root")),u()}},[[16,1,2]]]);
//# sourceMappingURL=main.fd2e3662.chunk.js.map