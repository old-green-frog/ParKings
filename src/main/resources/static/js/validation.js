let forms = document.querySelectorAll(`[id$="-update-form"]`);
let trs = Array.from(forms, (form, _) => form.closest("tr"));
let submits = document.querySelectorAll(`[id$="-button-update"]`);

function getTdValues(tr) {
    let tds = tr.getElementsByTagName("td");
    return Array.from([...tds].slice(1, -1), (el, _) => el.textContent);
}

function onClick(index) {
    let td_vals = getTdValues(trs[index]);
    let form = forms[index];
    let inputs = form.elements;

    for (let i = 0; i < inputs.length; i++) {
        inputs[i].value = inputs[i].value ? inputs[i].value : td_vals[i];
    }

    form.submit();
}


for (let i = 0; i < submits.length; i++) {
    submits[i].onclick = function() {onClick(i)};
}