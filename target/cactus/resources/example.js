var moment = datetime.moment;

datetime(dt);
datetime(dt2);


var picker = datetime(ind);

if (toggle.addEventListener) {
  toggle.addEventListener('click', toggler);
} else if (toggle.attachEvent) {
  toggle.attachEvent('onclick', toggler);
} else {
  toggle.onclick = toggler;
}

function toggler () {
  if (picker.destroyed) {
    picker.restore();
  } else {
    picker.destroy();
  }
  toggle.innerHTML = picker.destroyed ? 'Restore <code>datetime</code> instance!' : 'Destroy <code>datetime</code> instance!';
}

datetime(mm, { min: '2013-12-30', max: '2014-10-01' });
datetime(mmt, { min: '2014-04-30 19:45', max: '2014-09-01 08:30' });

datetime(iwe, {
  dateValidator: function (d) {
    return moment(d).day() !== 6;
  }
});

datetime(win, {
  dateValidator: function (d) {
    var m = moment(d);
    var y = m.year();
    var f = 'MM-DD';
    var start = moment('12-21', f).year(y).startOf('day');
    var end = moment('03-19', f).year(y).endOf('day');
    return m.isBefore(start) && m.isAfter(end);
  }
});

datetime(tim, {
  timeValidator: function (d) {
    var m = moment(d);
    var start = m.clone().hour(12).minute(59).second(59);
    var end = m.clone().hour(18).minute(0).second(1);
    return m.isAfter(start) && m.isBefore(end);
  }
});

datetime(inl).on('data', function (value) {
  inlv.innerText = inlv.textContent = value;
});

datetime(left, {
  time: false,
  dateValidator: datetime.val.beforeEq(right)
});

datetime(right, {
  time: false,
  dateValidator: datetime.val.afterEq(left)
});

datetime(leftInline, {
  time: false,
  dateValidator: datetime.val.beforeEq(rightInline)
});

datetime(rightInline, {
  time: false,
  dateValidator: datetime.val.afterEq(leftInline)
});

datetime(exa, {
  dateValidator: datetime.val.except('2014-08-01')
});

datetime(exb, {
  dateValidator: datetime.val.except('2014-08-02', '2014-08-06')
});

datetime(exc, {
  dateValidator: datetime.val.except(['2014-08-04', '2014-08-09'])
});

datetime(exd, {
  dateValidator: datetime.val.except([['2014-08-03', '2014-08-07'], '2014-08-15'])
});

datetime(exe, {
  dateValidator: datetime.val.only([
    ['2014-08-01', '2014-08-15'], '2014-08-22'
  ])
});

datetime(exf, {
  dateValidator: datetime.val.except([exb, exd, '2014-08-15'])
});
