const fs=require('node:fs');
const vm=require('node:vm');
const assert=require('node:assert/strict');
const html=fs.readFileSync('app/src/main/assets/lyrics_overlay.html','utf8');
const classes=new Set(['compact']);
const context={compactLineMode:'auto',pronunciationEnabled:true,lyricFontScale:1,innerHeight:130,
  ui:{compactLyric:{querySelector:()=>({classList:{contains:name=>name==='has-pronunciation'}})}},
  document:{body:{classList:{contains:name=>classes.has(name),toggle:(name,value)=>value?classes.add(name):classes.delete(name)}}}};
vm.createContext(context);
vm.runInContext(html.slice(html.indexOf('    function updateCompactLayout()'),html.indexOf('    function setFontScale(')),context);
context.updateCompactLayout();
assert.ok(classes.has('compact-three'),'legacy auto mode retains pronunciation in a tall window');
context.compactLineMode='single';
context.updateCompactLayout();
assert.ok(!classes.has('compact-two')&&!classes.has('compact-three'),'single mode remains one row in a tall window');
context.compactLineMode='double';
context.updateCompactLayout();
assert.ok(classes.has('compact-two')&&!classes.has('compact-three'),'double mode never becomes three rows with pronunciation enabled');
for(const percent of [35,100,150]){
  context.lyricFontScale=percent/100;
  context.innerHeight=Math.ceil(10+(30*1.15+23*1.15)*percent/100);
  context.updateCompactLayout();
  assert.ok(classes.has('compact-two'),'double rows stay visible across font sizes');
}
classes.delete('compact');
context.updateCompactLayout();
assert.ok(!classes.has('compact-two')&&!classes.has('compact-three'),'compact preference does not affect expanded/fullscreen layout');
classes.add('compact');
context.compactLineMode='auto';
context.lyricFontScale=1;
context.innerHeight=48;
context.updateCompactLayout();
assert.ok(!classes.has('compact-two')&&!classes.has('compact-three'),'old one-row compact size stays unchanged');
console.log('PASS: fixed one/two rows, pronunciation isolation, all font sizes, expanded mode and legacy sizing');
