$(document).ready(function() {
    validacion();
});

function validacion() {
    $(".numerico").keydown(function(e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                // Allow: Ctrl+A
                        (e.keyCode == 65 && e.ctrlKey === true) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40)) {
                    // let it happen, don't do anything
                    return;
                }
                // Ensure that it is a number and stop the keypress
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            });

    $('input').keyup(function() {
        if (!$(this).hasClass("ui-password")) {
            // Remember original caret position
            var caretPosition = getCaretPosition(this);

            // Uppercase-ize contents
            this.value = this.value.toLocaleUpperCase();

            // Reset caret position
            // (we ignore selection length, as typing deselects anyway)
            setCaretPosition(this, caretPosition);
        }
    });

    $(".alfabetico").keypress(function(event) {
        var inputValue = event.charCode;
        if ((inputValue > 0 && inputValue < 65) && (inputValue != 32) || (inputValue > 90 && inputValue < 97) || (inputValue > 122 && inputValue < 127) || (inputValue > 127 && inputValue < 163)) {
            event.preventDefault();
        }
    });
    console.info("cargando scripts 5");
}

function getCaretPosition(ctrl) {
    var CaretPos = 0;    // IE Support
    if (document.selection) {
        ctrl.focus();
        var Sel = document.selection.createRange();
        Sel.moveStart('character', -ctrl.value.length);
        CaretPos = Sel.text.length;
    }
    // Firefox support
    else if (ctrl.selectionStart || ctrl.selectionStart == '0') {
        CaretPos = ctrl.selectionStart;
    }

    return CaretPos;
}

function setCaretPosition(ctrl, pos) {
    if (ctrl.setSelectionRange) {
        ctrl.focus();
        ctrl.setSelectionRange(pos, pos);
    }
    else if (ctrl.createTextRange) {
        var range = ctrl.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
}
