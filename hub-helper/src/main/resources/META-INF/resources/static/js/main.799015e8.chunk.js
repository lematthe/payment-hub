(this.webpackJsonpwebapp=this.webpackJsonpwebapp||[]).push([[0],{22:function(e,t,c){},24:function(e,t,c){},30:function(e,t,c){"use strict";c.r(t);var n=c(0),a=c.n(n),l=c(10),r=c.n(l),s=(c(22),c(23),c(5)),i=c(6),o=c(8),j=c(7),d=(c.p,c(24),c(35)),b=c(32),h=c(33),x=c(34),O=c(1),u=function(e){var t=e.acks;return Object(O.jsxs)("div",{children:[Object(O.jsx)("center",{children:Object(O.jsx)("h1",{children:"Transaction Hub"})}),Object(O.jsxs)("table",{class:"pf-c-table pf-m-grid-md",role:"grid","aria-label":"Orphaned Acknowledgements",id:"table-basic",children:[Object(O.jsx)("caption",{children:"Orphaned Acknowledgements"}),Object(O.jsx)("thead",{children:Object(O.jsxs)("tr",{role:"row",children:[Object(O.jsx)("th",{role:"columnheader",scope:"col",children:"Acknowledgment ID"}),Object(O.jsx)("th",{role:"columnheader",scope:"col",children:"Transaction ID"}),Object(O.jsx)("th",{role:"columnheader",scope:"col",children:"Confirmed Amount"}),Object(O.jsx)("th",{role:"columnheader",scope:"col",children:"Notes"}),Object(O.jsx)("th",{role:"columnheader",scope:"col",children:"Status"})]})}),null===t||void 0===t?void 0:t.map((function(e){return Object(O.jsx)("tbody",{role:"rowgroup",children:Object(O.jsxs)("tr",{role:"row",children:[Object(O.jsx)("td",{role:"cell","data-label":"Acknowledgement ID",children:e.ackID}),Object(O.jsx)("td",{role:"cell","data-label":"Transaction ID",children:e.txID}),Object(O.jsx)("td",{role:"cell","data-label":"Confirmed Amount",children:e.confirmedAmount}),Object(O.jsx)("td",{role:"cell","data-label":"Status",children:e.ackNotes}),Object(O.jsx)("td",{role:"cell","data-label":"Notes",children:e.ackStatus})]})})})),"}"]})]})},p=function(e){Object(o.a)(c,e);var t=Object(j.a)(c);function c(e){var n;return Object(s.a)(this,c),(n=t.call(this,e)).state={activeTabKey:0,isBox:!1,acks:[],txs:[],processed:[]},n.handleTabClick=function(e,t){n.setState({activeTabKey:t}),0==t&&fetch("/ack").then((function(e){return e.json()})).then((function(e){n.setState({acks:e})})).catch(console.log)},n.toggleBox=function(e){n.setState({isBox:e})},n}return Object(i.a)(c,[{key:"render",value:function(){var e=this.state,t=e.activeTabKey,c=e.isBox;return Object(O.jsxs)("div",{children:[Object(O.jsxs)(d.a,{activeKey:t,onSelect:this.handleTabClick,isBox:c,children:[Object(O.jsx)(b.a,{eventKey:0,title:Object(O.jsx)(h.a,{children:"Orphaned Acknowledgements"}),children:Object(O.jsx)(u,{acks:this.state.acks})}),Object(O.jsx)(b.a,{eventKey:1,title:Object(O.jsx)(h.a,{children:"Containers"}),children:"Containers"}),Object(O.jsx)(b.a,{eventKey:2,title:Object(O.jsx)(h.a,{children:"Database"}),children:"Database"}),Object(O.jsx)(b.a,{eventKey:3,title:Object(O.jsx)(h.a,{children:"Server"}),children:"Server"}),Object(O.jsx)(b.a,{eventKey:4,title:Object(O.jsx)(h.a,{children:"System"}),children:"System"}),Object(O.jsx)(b.a,{eventKey:6,title:Object(O.jsx)(h.a,{children:"Network"}),children:"Network"})]}),Object(O.jsx)("div",{style:{marginTop:"20px"},children:Object(O.jsx)(x.a,{label:"isBox",isChecked:c,onChange:this.toggleBox,"aria-label":"show box variation checkbox",id:"toggle-box",name:"toggle-box"})})]})}}]),c}(a.a.Component),v=function(e){Object(o.a)(c,e);var t=Object(j.a)(c);function c(){return Object(s.a)(this,c),t.apply(this,arguments)}return Object(i.a)(c,[{key:"render",value:function(){return Object(O.jsxs)("div",{children:[Object(O.jsxs)("div",{children:["Number of objects : ",this.state.acks.length]}),Object(O.jsx)(p,{})]})}}]),c}(n.Component),m=function(e){e&&e instanceof Function&&c.e(3).then(c.bind(null,36)).then((function(t){var c=t.getCLS,n=t.getFID,a=t.getFCP,l=t.getLCP,r=t.getTTFB;c(e),n(e),a(e),l(e),r(e)}))};r.a.render(Object(O.jsx)(a.a.StrictMode,{children:Object(O.jsx)(v,{})}),document.getElementById("root")),m()}},[[30,1,2]]]);
//# sourceMappingURL=main.799015e8.chunk.js.map