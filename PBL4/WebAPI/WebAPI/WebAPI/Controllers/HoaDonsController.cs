using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Net;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class HoaDonsController : ControllerBase
    {
        private readonly MyDbContext _context;
        public HoaDonsController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetHoaDons()
        {
            try
            {
                List<HoaDonModel> listModel = new List<HoaDonModel>();
                foreach (HoaDon i in _context.HoaDons.ToList())
                {
                    HoaDonModel hoaDonModel = new HoaDonModel
                    {
                        ID_HoaDon = i.ID_HoaDon,
                        NgayLap = i.NgayLap,
                        TongTien = i.TongTien,
                        ID_Account = i.ID_Account
                    };
                    listModel.Add(hoaDonModel);
                }
                return Ok(listModel);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IActionResult GetHoaDon(int? id)
        {
            try
            {
                var g = _context.HoaDons.FirstOrDefault(u => u.ID_HoaDon == id);
                if (g != null)
                {
                    HoaDonModel hoaDonModel = new HoaDonModel
                    {
                        ID_HoaDon = g.ID_HoaDon,
                        NgayLap = g.NgayLap,
                        TongTien = g.TongTien,
                        ID_Account = g.ID_Account
                    };
                    return Ok(hoaDonModel);
                }
                else
                {
                    return NotFound("Not Found");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut]
        public IActionResult PutHoaDon(HoaDonModel hoaDonModel)
        {
            try
            {
                var g = _context.HoaDons.FirstOrDefault(u => u.ID_HoaDon == hoaDonModel.ID_HoaDon);
                if (g != null)
                {
                    g.NgayLap = hoaDonModel.NgayLap;
                    g.TongTien = hoaDonModel.TongTien;
                    g.ID_Account = hoaDonModel.ID_Account;
                    _context.SaveChanges();
                    return Ok(hoaDonModel);
                }
                else
                {
                    return NotFound("Not Found");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public IActionResult PostHoaDon(HoaDonModel hoaDonModel)
        {
            try
            {
                HoaDon hoaDon = new HoaDon
                {
                    NgayLap = hoaDonModel.NgayLap,
                    TongTien = hoaDonModel.TongTien,
                    ID_Account = hoaDonModel.ID_Account
                };
                _context.HoaDons.Add(hoaDon);
                _context.SaveChanges();
                return Ok(hoaDon.ID_HoaDon);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }


        [HttpDelete]
        public async Task<IActionResult> DeleteHoaDon(int? id)
        {
            var hoadon = await _context.HoaDons.FindAsync(id);
            if (hoadon == null)
            {
                return NotFound();
            }
            _context.HoaDons.Remove(hoadon);
            await _context.SaveChangesAsync();
            return Ok();
        }

    }
}

