using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Net;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class ChiTietHoaDonsController : ControllerBase
    {
        private readonly MyDbContext _context;
        public ChiTietHoaDonsController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetChiTietHoaDons(int id)
        {
            try
            {
                List<ChiTietHoaDonModel> list = new List<ChiTietHoaDonModel>();
                var g = from ChiTietHoaDon in _context.ChiTietHoaDons
                        where ChiTietHoaDon.ID_HoaDon == id
                        select ChiTietHoaDon;
                if (g.Count() > 0)
                {
                    foreach (ChiTietHoaDon i in g)
                    {
                        ChiTietHoaDonModel model = new ChiTietHoaDonModel
                        {
                            ID_HoaDon = i.ID_HoaDon,
                            ID_Sach = i.ID_Sach,
                            SoLuong = i.SoLuong,
                            TongTien = i.TongTien
                        };
                        list.Add(model);
                    }
                    return Ok(list);
                }
                else
                {
                    return Ok(null);
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IActionResult GetChiTietHoaDon(int? id)
        {
            try
            {
                var g = _context.ChiTietHoaDons.FirstOrDefault(u => u.ID_ChiTietHoaDon == id);
                if (g != null)
                {
                    ChiTietHoaDonModel model = new ChiTietHoaDonModel
                    {
                        ID_HoaDon = g.ID_HoaDon,
                        ID_Sach = g.ID_Sach,
                        SoLuong = g.SoLuong,
                        TongTien = g.TongTien
                    };
                    return Ok(model);
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
        public IActionResult PostChiTietHoaDon(ChiTietHoaDonModel Model)
        {
            try
            {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon
                {
                    ID_HoaDon = Model.ID_HoaDon,    
                    ID_Sach = Model.ID_Sach,
                    SoLuong = Model.SoLuong,
                    TongTien = Model.TongTien,
                };
                _context.ChiTietHoaDons.Add(chiTietHoaDon);
                _context.SaveChanges();
                return Ok(Model);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteChiTietHoaDon(int? id)
        {
            var chiTietHoaDon = await _context.ChiTietHoaDons.FindAsync(id);
            if (chiTietHoaDon == null)
            {
                return NotFound();
            }
            _context.ChiTietHoaDons.Remove(chiTietHoaDon);
            await _context.SaveChangesAsync();
            return Ok("Success");
        }

    }
}
