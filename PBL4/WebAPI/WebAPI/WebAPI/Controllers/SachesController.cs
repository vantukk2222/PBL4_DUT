using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Security.Principal;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class SachesController : ControllerBase
    {
        private readonly MyDbContext _context;

        public SachesController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetSaches()
        {
            try
            {
                List<SachModel> list = new List<SachModel>();
                foreach (Sach i in _context.Saches.ToList())
                {
                    SachModel sachModel = new SachModel
                    {
                        ID_Sach = i.ID_Sach,
                        TenSach = i.TenSach,
                        Theloai = i.Theloai,
                        ImgSach = i.ImgSach,
                        TenTacGia = i.TenTacGia,
                        SolanTaiBan = Convert.ToInt32(i.SolanTaiBan),
                        NamXuatBan = i.NamXuatBan,
                        GiaBan = i.GiaBan,
                        SoLuong = Convert.ToInt32(i.SoLuong)
                    };
                    list.Add(sachModel);
                }
                return Ok(list);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IActionResult GetSach(int id)
        {
            try
            {
                var g = _context.Saches.FirstOrDefault(s => s.ID_Sach == id);
                if (g != null)
                {
                    SachModel sachModel = new SachModel
                    {
                        ID_Sach = g.ID_Sach,
                        TenSach = g.TenSach,
                        Theloai = g.Theloai,
                        ImgSach = g.ImgSach,
                        TenTacGia = g.TenTacGia,
                        SolanTaiBan = g.SolanTaiBan,
                        NamXuatBan = g.NamXuatBan,
                        GiaBan = g.GiaBan,
                        SoLuong = g.SoLuong
                    };
                    return Ok(sachModel);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut]
        public IActionResult PutSach(SachModel sachModel)
        {
            try
            {
                var g = _context.Saches.FirstOrDefault(u => u.ID_Sach == sachModel.ID_Sach);
                if (g != null)
                {
                    g.TenSach = sachModel.TenSach;
                    g.Theloai = sachModel.Theloai;
                    g.ImgSach = sachModel.ImgSach;
                    g.TenTacGia = sachModel.TenTacGia;
                    g.SolanTaiBan = sachModel.SolanTaiBan;
                    g.NamXuatBan = sachModel.NamXuatBan;
                    g.GiaBan = sachModel.GiaBan;
                    g.SoLuong = sachModel.SoLuong;
                    _context.SaveChanges();
                    return Ok("Success ID : " + sachModel.ID_Sach);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpPut]
        public IActionResult PutSachSoLuong(int ID_Sach, int Soluong)
        {
            try
            {
                var g = _context.Saches.FirstOrDefault(u => u.ID_Sach == ID_Sach);
                if (g != null)
                {
                   
                    g.SoLuong -= Soluong;
                    _context.SaveChanges();
                    return Ok(ID_Sach);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public IActionResult PostSach(SachModelPost sachModelPost)
        {
            try
            {
                Sach sach = new Sach
                {
                    TenSach = sachModelPost.TenSach,
                    Theloai = sachModelPost.Theloai,
                    ImgSach = sachModelPost.Img_Sach,
                    TenTacGia = sachModelPost.TenTacGia,
                    SolanTaiBan = sachModelPost.SolanTaiBan,
                    NamXuatBan = sachModelPost.NamXuatBan,
                    GiaBan = sachModelPost.GiaBan,
                    SoLuong = sachModelPost.SoLuong
                };
                _context.Saches.Add(sach);
                _context.SaveChanges();
                return Ok(sachModelPost);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteSach(int id)
        {
            var sach = await _context.Saches.FindAsync(id);
            if (sach == null)
            {
                return NotFound();
            }
            _context.Saches.Remove(sach);
            await _context.SaveChangesAsync();
            return Ok();
        }

    }
}
