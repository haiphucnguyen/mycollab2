$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback25("function NHc(){}\nfunction PHc(){}\nfunction RHc(){}\nfunction fpd(){g$b.call(this)}\nfunction h1b(a,b){return IL(a.G.lo(b))}\nfunction FTd(){KZb.call(this);this.I=OFe;this.a=new k4d}\nfunction qTc(c,a){var b=c;a.notifyChildrenOfSizeChange=Y8d(function(){b.Wk()})}\nfunction yTc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction nTc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction mTc(a,b){var c,d;for(c=F0d(new G0d(a.f));c.a.Ug();){d=IL(L0d(c));if(PL(a.f.lo(d))===PL(b)){return d}}return null}\nfunction rTc(a,b){var c,d;d=mTc(a,b);d!=null&&a.f.oo(d);c=GL(a.a.lo(b),519);if(c){a.a.oo(b);return bd(a,c)}else if(b){return bd(a,b)}return false}\nfunction oTc(a){var b,c,d;d=(qub(),a._b).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];oub.Ag(c,Xee)}}\nfunction sTc(a,b){var c,d,e;if((Zt(),b).hasAttribute(_ze)){e=cu(b,_ze);a.e.no(e,b);It(b,'')}else{d=(qub(),zwb(b));for(c=0;c<d;c++){sTc(a,ywb(b,c))}}}\nfunction tTc(a,b,c){var d,e;if(!b){return}d=HL(a.e.lo(c));if(!d&&a.d){throw new gZd('No location '+c+' found')}e=GL(a.f.lo(c),9);if(e==b){return}!!e&&rTc(a,e);a.d||(d=(qub(),a._b));Tc(a,b,(qub(),d));a.f.no(c,b)}\nfunction uTc(a,b){var c,d,e;d=b.Pd();e=GL(a.a.lo(d),519);if(x9b(b.Nd())){if(!e){c=mTc(a,d);bd(a,d);e=new F9b(b,a.b);Sc(a,e,HL(a.e.lo(c)));a.a.no(d,e)}s9b(e.a)}else{if(e){c=mTc(a,d);bd(a,e);Sc(a,d,HL(a.e.lo(c)));a.a.oo(d)}}}\nfunction JHc(c){var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.ok(bmb,KFe,d);var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.ok(bmb,LFe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.ok(bmb,MFe,d)}\nfunction vTc(){var a;cd.call(this);this.e=new k4d;this.f=new k4d;this.a=new k4d;lb(this,(qub(),Ov($doc)));a=this._b.style;Kx(a,soe,(Qx(),i9d));Kx(a,Eqe,(sB(),Oae));Kx(a,Qqe,Oae);(H3b(),!G3b&&(G3b=new Y3b),H3b(),G3b).a.g&&Kx(a,z9d,(xA(),bae));Gt(this._b,OFe);Hb(this._b,Mye,true)}\nfunction epd(a){var b,c;if(a.a){return}c=(!a.F&&(a.F=rg(a)),GL(GL(GL(a.F,6),114),387)).c;b=(!a.F&&(a.F=rg(a)),GL(GL(GL(a.F,6),114),387)).b;if(c!=null){b=h1b(a.u,'layouts/'+c+'.html');b==null&&It(bb(GL(Zg(a),242)),'<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}b!=null&&pTc(GL(Zg(a),242),b,i1b(a.u));a.a=true}\nfunction pTc(a,b,c){var d;b=lTc(a,b);d=Cbc(c+'/layouts/');b=k$d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',NFe+d+'$3\"');b=k$d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',NFe+d+'$3\"');b=k$d(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');It((qub(),a._b),b);a.e.Ic();sTc(a,a._b);oTc(a);a.c=yub(a._b);!a.c&&(a.c=a._b);qTc(a,a.c);a.d=true}\nfunction lTc(a,b){var c,d,e,f,g,h,i,j;b=k$d(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';i=f.indexOf('<script',0);while(i>0){h+=b.substr(d,i-d);i=f.indexOf('>',i);e=f.indexOf('<\\/script>',i);a.i+=b.substr(i+1,e-(i+1))+';';g=d=e+9;i=f.indexOf('<script',g)}h+=u$d(b,d,b.length-d);f=h.toLowerCase();j=f.indexOf('<body');if(j<0){h=h}else{j=f.indexOf('>',j)+1;c=f.indexOf('<\\/body>',j);c>j?(h=h.substr(j,c-j)):(h=u$d(h,j,h.length-j))}return h}\nvar KFe='templateContents',LFe='childLocations',MFe='templateName',NFe='<$1 $2src=\"',OFe='v-customlayout';Cqb(1790,1,eqe);_.Ie=function MHc(){fKc(this.b,bmb,slb);XJc(this.b,que,Vfb);ZJc(this.b,bmb,rue,new NHc);ZJc(this.b,Vfb,rue,new PHc);ZJc(this.b,Zab,rue,new RHc);dKc(this.b,Vfb,zae,new PJc(Zab));dKc(this.b,Vfb,oae,new PJc(bmb));JHc(this.b);bKc(this.b,bmb,KFe,new PJc(sob));bKc(this.b,bmb,LFe,new QJc(Lve,zL(vL(u9,1),sue,4,0,[new PJc(Wkb),new PJc(sob)])));bKc(this.b,bmb,MFe,new PJc(sob));Vpc((!Opc&&(Opc=new _pc),Opc),this.a.d)};Cqb(1792,1,Bxe,NHc);_.ik=function OHc(a,b){return new FTd};var w8=wYd(Ase,'ConnectorBundleLoaderImpl/25/1/1',1792);Cqb(1793,1,Bxe,PHc);_.ik=function QHc(a,b){return new fpd};var x8=wYd(Ase,'ConnectorBundleLoaderImpl/25/1/2',1793);Cqb(1794,1,Bxe,RHc);_.ik=function SHc(a,b){return new vTc};var y8=wYd(Ase,'ConnectorBundleLoaderImpl/25/1/3',1794);Cqb(242,202,{14:1,11:1,13:1,12:1,24:1,30:1,15:1,26:1,10:1,9:1,242:1,19:1},vTc);_.Hc=function wTc(a){throw new X$d};_.Ic=function xTc(){Nc(this);this.f.Ic();this.a.Ic()};_.Wk=function zTc(){};_.sc=function ATc(a){Ub(this,a);qub();if(lwb((Zt(),a).type)==Xee){i9b(this,true);kwb(a,true)}};_.tc=function BTc(){Vb(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function CTc(a){return rTc(this,a)};_.jc=function DTc(a){Gt((qub(),this._b),a);Hb(this._b,Mye,true)};_.d=false;_.i='';var Zab=wYd(qae,'VCustomLayout',242);Cqb(1791,499,{7:1,16:1,120:1,91:1,132:1,25:1,34:1,33:1,31:1,151:1,248:1,32:1,3:1},fpd);_.Nd=function gpd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.zd=function hpd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.yi=function ipd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.Pd=function jpd(){return GL(Zg(this),242)};_.Bd=function kpd(){GL(Zg(this),242).b=this.u;GL(Zg(this),242).g=this.w};_.Fi=function lpd(){nTc((GL(Zg(this),242),yub(bb(GL(Zg(this),242)))))};_.ke=function mpd(b){var c,d,e,f,g,h;epd(this);for(d=Zh(this).Pc();d.Ug();){c=GL(d.Vg(),16);e=IL((!this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)).a.lo(c));try{tTc(GL(Zg(this),242),c.Pd(),e)}catch(a){a=zqb(a);if(!KL(a,38))throw yqb(a)}}for(g=b.a.Pc();g.Ug();){f=GL(g.Vg(),16);if(f.xd()==this){continue}h=f.Pd();h.qc()&&rTc(GL(Zg(this),242),h)}};_.Dd=function npd(a){_g(this,a);epd(this);yTc(GL(Zg(this),242).i);GL(Zg(this),242).i=null};_.le=function opd(a){uTc(GL(Zg(this),242),a)};_.ji=function ppd(a,b){};_.a=false;var Vfb=wYd('com.vaadin.client.ui.customlayout',aye,1791);Cqb(387,114,{6:1,41:1,114:1,387:1,3:1},FTd);var bmb=wYd('com.vaadin.shared.ui.customlayout','CustomLayoutState',387);Y8d(Vq)(25);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-25.js\n")
